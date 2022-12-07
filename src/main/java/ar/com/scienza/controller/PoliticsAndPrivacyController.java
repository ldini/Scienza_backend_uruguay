package ar.com.scienza.controller;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.PoliticsAndPrivacyDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PoliticsAndPrivacyController {

    @Value("${location.files}")
    String locationFiles;

    @Value("${path.docs}")
    String pathDocs;

    @Autowired
    private Transaction transaction;


    @Authorize("*")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/politics-privacy",
            produces = "application/json"
    )
    public @ResponseBody
    ResponseDto getPoliticsPrivacy(
            @RequestHeader(value="AppID") String appId,
            @RequestHeader(value="Token") String token,
            @RequestHeader(value="SapId") Long sapId,
            @RequestHeader(value="DeviceType") String deviceType
    ) {

        PoliticsAndPrivacyDto response = null;

        try
        {
            String html = new String(Files.readAllBytes(Paths.get(locationFiles + pathDocs + "politicas-privacidad-uy.html")));

            response = new PoliticsAndPrivacyDto();
            response.setHtml(html);
        }
        catch(Exception ex) {
            ScienzaLogger.logError(ex);
            return ResponseDto.newError(transaction, ex.getMessage());
        }

        return ResponseDto.newSuccess(transaction, response);
    }
}

