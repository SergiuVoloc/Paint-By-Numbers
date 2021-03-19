package sergiu.voloc.PaintByNumbers.Service;

import sergiu.voloc.PaintByNumbers.Model.File_Storage;
import sergiu.voloc.PaintByNumbers.Repository.FileStorageRepository;
import sergiu.voloc.PaintByNumbers.Utility.FileStorageException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Autowired
    ServletContext context;

    @Value("${app.upload.dir}")
    public String uploadDir;

    public File_Storage read(UUID id){
        return fileStorageRepository.findById(id).orElse(null);
    }

    public File_Storage uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(fileName) + "." + ext);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            File_Storage f = new File_Storage();
            f.setName(file.getOriginalFilename());
            f.setPath(copyLocation.toString());
            f.setExtension(ext);
            f.setSize(file.getSize());
            f.setMime_type(file.getContentType());
            return fileStorageRepository.save(f);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }


}
