package br.com.devpelinson.spring_boot_essentials.controller;

import br.com.devpelinson.spring_boot_essentials.database.model.AlunosEntity;
import br.com.devpelinson.spring_boot_essentials.dto.AlunoDTO;
import br.com.devpelinson.spring_boot_essentials.exception.BadRequestException;
import br.com.devpelinson.spring_boot_essentials.service.AlunosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunosController {

    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunoDTO alunoDTO) throws BadRequestException {
        alunosService.criarAluno(alunoDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlunosEntity> findAll(){
        return alunosService.findAll();
    }
}
