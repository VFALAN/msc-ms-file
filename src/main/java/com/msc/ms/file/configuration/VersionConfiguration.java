package com.msc.ms.file.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VersionConfiguration {


    public VersionConfiguration(@Value("${msc.app.version}") String pAppMscVersion) {
        log.info("Running Management System College File Micro Service Version msc-ms-file: {}", pAppMscVersion);
    }
}
