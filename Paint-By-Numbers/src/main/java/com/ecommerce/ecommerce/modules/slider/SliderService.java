package com.ecommerce.ecommerce.modules.slider;

import com.ecommerce.ecommerce.modules.fileStorage.FileStorageService;
import com.ecommerce.ecommerce.modules.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SliderService {

    @Autowired
    private SliderRepository sliderRepository;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ObjectUtils objectUtils;

    public Iterable<Slider> all(){
        return sliderRepository.findAll();
    }

    public Slider read(@PathVariable(value = "id") UUID id){
        return sliderRepository.findById(id).orElseThrow();
    }

    public void create(String title, String subtitle, MultipartFile file){
        Slider n = new Slider(title, subtitle, fileStorageService.uploadFile(file));
        sliderRepository.save(n);
    }
    public void update(@PathVariable(value = "id") UUID id, Slider o){
        Slider c = sliderRepository.findById(id).orElseThrow();
        sliderRepository.save((Slider) objectUtils.update(c,o));
    }

    public void delete(@PathVariable(value = "id") UUID id){
        sliderRepository.deleteById(id);
    }
}
