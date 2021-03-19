package sergiu.voloc.PaintByNumbers.Repository;

import sergiu.voloc.PaintByNumbers.Model.File_Storage;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileStorageRepository extends CrudRepository<File_Storage, UUID> {
}
