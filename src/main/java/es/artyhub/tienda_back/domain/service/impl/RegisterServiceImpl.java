package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.RegisterDto;
import es.artyhub.tienda_back.domain.repository.RegisterRepository;
import es.artyhub.tienda_back.domain.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    
    private final RegisterRepository registerRepository;

    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }
    
    @Override
    public void register(RegisterDto registerDto) {
        registerRepository.register(registerDto);
    }
}
