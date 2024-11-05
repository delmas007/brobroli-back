package ci.digitalacademy.com.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FiltreStorageService {

    String upload(MultipartFile file) throws IOException;
}
