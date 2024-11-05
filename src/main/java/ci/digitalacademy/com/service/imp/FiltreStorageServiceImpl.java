package ci.digitalacademy.com.service.imp;

import ci.digitalacademy.com.service.FiltreStorageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FiltreStorageServiceImpl implements FiltreStorageService {

    private final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile file) throws IOException {

        Map params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);

        return (String) uploadResult.get("url");
    }
}
