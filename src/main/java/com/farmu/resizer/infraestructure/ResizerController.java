package com.farmu.resizer.infraestructure;

import com.farmu.resizer.application.ResizerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resizer")
public class ResizerController {

    private ResizerService resizerService;

    public ResizerController(ResizerService resizerService) {
        this.resizerService = resizerService;
    }

    @PostMapping(value="/resize", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> resize(@RequestBody ResizeDTO resizeDTO) {
        return ResponseEntity.ok(this.resizerService.resize(resizeDTO));
    }
}