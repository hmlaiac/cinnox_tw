package com.example.service.services;

import com.example.service.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineService {
    @Autowired
    private LineRepository lineRepository;


}
