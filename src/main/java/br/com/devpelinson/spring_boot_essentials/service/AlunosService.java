package br.com.devpelinson.spring_boot_essentials.service;

import br.com.devpelinson.spring_boot_essentials.database.model.AlunosEntity;
import br.com.devpelinson.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.devpelinson.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.devpelinson.spring_boot_essentials.dto.AlunoDto;
import br.com.devpelinson.spring_boot_essentials.exception.BadRequestException;
import br.com.devpelinson.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {

    private final IAlunosRepository alunosRepository;

    public void criarAluno(AlunoDto alunoDTO) throws BadRequestException {
        AlunosEntity aluno = alunosRepository.findByEmail(alunoDTO.getEmail())
                .orElse(null);

        if (aluno != null){
            throw new BadRequestException("Aluno ja cadastrado com este email");
        }

        alunosRepository.save(AlunosEntity.builder()
                        .nome(alunoDTO.getNome())
                        .email(alunoDTO.getEmail())
                .build());
    }

    public List<AlunosEntity> findAll(){
        return alunosRepository.findAll();
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException{
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno nao encontrado"));
        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacoesFisica();
        if(avaliacao == null){
            throw new NotFoundException("Avaliacao fisica nao encontrada");
        }

        return avaliacao;
    }
}
