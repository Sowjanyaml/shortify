package com.example.shortify.controller;

import com.example.shortify.dto.LongUrlDto;
import com.example.shortify.dto.ShortUrlDto;
import com.example.shortify.exception.UrlInvalidException;
import com.example.shortify.exception.UrlNotFoundException;
import com.example.shortify.service.ShortifyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/shortify")
public class ShortifyController {
    private final ShortifyService shortifyService;

    public ShortifyController(ShortifyService shortifyService) {
        this.shortifyService = shortifyService;
    }
    @Operation(summary = "Shortens Long URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns key corresponding to the URL passed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShortUrlDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid URL supplied",
                    content = @Content) })
    @PostMapping()
    public ShortUrlDto shortenUrl(@RequestBody LongUrlDto url) throws UrlInvalidException {
        return shortifyService.shortenUrl(url);
    }

    @Operation(summary = "Get a full url by its key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the url corresponding to the key",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Key not found",
                    content = @Content) })
    @GetMapping(value = "/{key}")
    public String getUrl(@Parameter(description = "key of the URL to be searched") @PathVariable String key) throws UrlNotFoundException {
        return shortifyService.getUrlByKey(key);
    }
}
