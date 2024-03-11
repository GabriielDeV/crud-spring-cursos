package com.gabriel.crudsrping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.gabriel.crudsrping.Repository.CursoRepository;
import com.gabriel.crudsrping.model.Curso;

@Service
public class CursoService {

    final CursoRepository cursoRepository;
    
    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }
    
    public List<Curso> listarCurso(){
        return cursoRepository.findAll();
    }

    public Curso salvar (Curso curso){
        return  cursoRepository.save(curso);
    }

    public Optional<Curso> getCursoId(Long id){
        return cursoRepository.findById(id);
    }

    public void deletar(Long id){
        cursoRepository.deleteById(id);
    }

    public Curso atualizarcurso(long id, Curso cursonovo){
        Optional<Curso> cursodb = cursoRepository.findById(id);
        if(cursodb.isPresent()){
            Curso cursoAtualizado = cursodb.get();
            cursoAtualizado.setNome(cursonovo.getNome());
            cursoAtualizado.setCategoria(cursonovo.getCategoria());
            return cursoRepository.save(cursoAtualizado);
        }else{
            throw new RuntimeException("Curso não encontrado com o ID: " + id);
        }
    }
}
