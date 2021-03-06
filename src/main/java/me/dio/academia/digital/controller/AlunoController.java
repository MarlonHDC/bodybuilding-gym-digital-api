package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;
    @Autowired
    private AlunoRepository repository;

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form) {

        return service.create(form);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        return service.getAllAvaliacaoFisicaId(id);
    }
    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento)
    {
        return service.getAll(dataDeNascimento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/atualiza/{id}")
    public Aluno update(@PathVariable Long id, @RequestBody AlunoRepository alunoRepository) {
        Aluno formUpdate = alunoRepository.findById(id).get();
        BeanUtils.copyProperties(alunoRepository, formUpdate, "id" );
        return alunoRepository.save(formUpdate);

    }



}
