package com.example.controller;

import com.example.Service.PdfService;
import com.example.entity.Booking;
import com.example.entity.Property;
import com.example.entity.Room;
import com.example.reposetry.BookingRepository;
import com.example.reposetry.PropertyRepository;
import com.example.reposetry.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pdf")
public class BookingController {
    private final PdfService pdfService;
    private final PropertyRepository propertyRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    @PostMapping("/create-booking")
    public String createBook(@RequestParam long propertyId,
        @RequestParam String type,
        @RequestBody Booking  booking

    ){
        Property property = propertyRepository.findById(propertyId).get();
       Room  room = roomRepository.findByTypeAndProperty(type, property);
        if (room.getCount()==0){
            return "no room available";
        }
        Booking save = bookingRepository.save(booking);
        if (save!=null){
            room.setCount(room.getCount()-1);
            room.setDate(new Date());
            roomRepository.save(room);
        }
        pdfService.generateBookPdf("S:\\Hms.booking\\confirmation-order "+save.getId()+ ".pdf" , property ,booking );
        return "booking created Successfully";
    }




    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestParam("image") MultipartFile imageFile) throws Exception {
        // Convert MultipartFile to byte[] for PDF generation
        byte[] imageBytes = imageFile.getBytes();

        // Generate PDF with image
        pdfService.createPdfWithImage(imageBytes);

        return new ResponseEntity<>("PDF generated successfully at the specified path!", HttpStatus.OK);
    }
}
