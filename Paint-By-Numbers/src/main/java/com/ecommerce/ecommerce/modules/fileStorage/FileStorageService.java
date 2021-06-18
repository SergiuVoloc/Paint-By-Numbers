package com.ecommerce.ecommerce.modules.fileStorage;

import com.ecommerce.ecommerce.modules.product.Product;
import com.ecommerce.ecommerce.modules.product.ProductServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    @Autowired
    private FileStorageRepository fileStorageRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    public String indexPath = System.getProperty("user.dir") + "/data/lucene/indexedFiles";

    public FileStorageService(){ }

    public FileStorage read(UUID id){
        return fileStorageRepository.findById(id).orElse(null);
    }

    public FileStorage uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            Path path = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(fileName) + "." + ext);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return getFileStorage(file, fileName, ext, path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }


    public FileStorage uploadFile(MultipartFile file, String pid) {
        try {
            String fileName = UUID.randomUUID().toString();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            Path path = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(fileName) + "." + ext);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            //Lucene File indexing
            if (ext.equals("pdf")) {
                addFileToIndex(path, pid);
            }

            return getFileStorage(file, fileName, ext, path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }



    private FileStorage getFileStorage(MultipartFile file, String fileName, String ext, Path path) {
        FileStorage f = new FileStorage();
        f.setName(file.getOriginalFilename());
        f.setInternalName(fileName);
        f.setPath(path.toString());
        f.setExtension(ext);
        f.setPublic_path("uploads/" + StringUtils.cleanPath(fileName) + "." + ext );
        f.setSize(file.getSize());
        f.setMime_type(file.getContentType());
        return fileStorageRepository.save(f);
    }

    public void delete(UUID id){
        FileStorage f = fileStorageRepository.findById(id).orElseThrow();
        File file = new File(f.getPath());
        deleteFileFromIndex(id);
        fileStorageRepository.deleteById(id);
        file.delete();
    }
    public FileStorage findByName(String imageName) {
        return fileStorageRepository.findByName(imageName);
    }

    public List<Product> search(String textToFind) {
        List<Product> results = new ArrayList<>();
        //Create lucene searcher. It search over a single IndexReader.
        String INDEX_DIR = System.getProperty("user.dir") + "/data/lucene/indexedFiles";
        try {
            Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));

            //It is an interface for accessing a point-in-time view of a lucene index
            IndexReader reader = DirectoryReader.open(dir);

            //Index searcher
            IndexSearcher searcher = new IndexSearcher(reader);

            //Search indexed contents using search term
            //Create search query
            QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
            Query query = qp.parse(textToFind);
            //search the index
            TopDocs foundDocs = searcher.search(query, 10);
            //Let's print out the path of files which have searched term
            for (ScoreDoc sd : foundDocs.scoreDocs)
            {
                Document d = searcher.doc(sd.doc);
                results.add(productServiceImpl.read(UUID.fromString(d.get("product"))));
//                results.add(fileStorageRepository.findByInternalName(d.get("product")));
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return results;
    }

    public void deleteFileFromIndex(UUID id) {
        FileStorage f = fileStorageRepository.findById(id).orElseThrow();
        File file = new File(f.getPath());
        try
        {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open( Paths.get(indexPath) );

            //analyzer with the default stop words
            Analyzer analyzer = new StandardAnalyzer();

            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);

            writer.deleteDocuments(new Term("uuid", f.getInternalName()));
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addFileToIndex(Path file, String pid) {
        try
        {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open( Paths.get(indexPath) );

            //analyzer with the default stop words
            Analyzer analyzer = new StandardAnalyzer();

            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);

            //Its recursive method to iterate all files and directories
            indexDoc(writer, file, pid);

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void indexDoc(IndexWriter writer, Path file, String pid) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            File f = new File(System.getProperty("user.dir") + "/src/main/resources/static/uploads/" + file.getFileName().toString());

            //Convert from pdf to txt
            String parsedText;
            PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            pdDoc.close();

            //Create lucene Document
            Document doc = new Document();
            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            doc.add(new StringField("uuid", file.getFileName().toString().replaceAll("(?<!^)[.](.*)", ""), Field.Store.YES));
            doc.add(new StringField("product", pid, Field.Store.YES));
            doc.add(new TextField("contents", parsedText, Field.Store.YES));

            //Updates a document by first deleting the document(s)
            //containing <code>term</code> and then adding the new
            //document.  The delete and then add are atomic as seen
            //by a reader on the same index
            writer.updateDocument(new Term("uuid", file.getFileName().toString().replaceAll("(?<!^)[.](.*)", "")), doc);
        }
    }

}
