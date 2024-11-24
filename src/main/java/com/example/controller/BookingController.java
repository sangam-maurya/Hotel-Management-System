package com.example.controller;

import com.example.Service.PdfService;
import com.example.Service.TwilioSmsService;
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
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pdf")
public class BookingController {
    private final PdfService pdfService;
    private final PropertyRepository propertyRepository;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final TwilioSmsService twilioSmsService;
    @PostMapping("/create-booking")
    public ResponseEntity<?> createBook(@RequestParam long propertyId,
        @RequestParam String type,
        @RequestBody Booking  booking

    ){
        Property property = propertyRepository.findById(propertyId).get();
        List<Room> rooms = roomRepository.findByTypeAndProperty(booking.getFromDate(), booking.getToDate() ,type ,property);

        for (Room room:rooms) {
        if (room.getCount()==0){
            return new ResponseEntity<>("No Rooms for this date " + room.getDate() ,
                    HttpStatus.BAD_REQUEST);
        }
        }
        Booking save = bookingRepository.save(booking);

        if (save!=null) {
            for (Room room : rooms) {
                room.setCount(room.getCount() - 1);
                roomRepository.save(room);
            }
        }
        
        pdfService.generateBookPdf("S:\\Hms.booking\\confirmation-order "+save.getId()+ ".pdf" , property ,booking );
        twilioSmsService.sendSms("+917318383616" , "+19787248108" , "Booking Confirmed" + booking.getId());
        return new ResponseEntity<>(rooms , HttpStatus.OK);
    }




    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(@RequestParam("image") MultipartFile imageFile) throws Exception {
        // Convert MultipartFile to byte[] for PDF generation
        byte[] imageBytes = imageFile.getBytes();

        // Generate PDF with image
        pdfService.createPdfWithImage(imageBytes);
        twilioSmsService.sendSms("", "", "");
        return new ResponseEntity<>("PDF generated successfully at the specified path!", HttpStatus.OK);
    }
}
