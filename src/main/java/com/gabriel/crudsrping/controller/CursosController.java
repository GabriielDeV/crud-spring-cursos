package com.gabriel.crudsrping.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabriel.crudsrping.model.Curso;
import com.gabriel.crudsrping.service.CursoService;
import java.util.Optional;

import java.awt.*;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "http://localhost:4200")
public class CursosController {

    final CursoService cursoService;

    public CursosController (CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarCurso(){
        return cursoService.listarCurso();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Curso> getCurso (@PathVariable Long id){
        return cursoService.getCursoId(id).map(curso -> ResponseEntity.ok().body(curso)).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> salvarCurso (@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(cursoService.salvar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> editarCurso(@PathVariable long id, @RequestBody Curso curso) {
        try{
            Curso cursoAtualizado = cursoService.atualizarcurso(id, curso);
            return ResponseEntity.ok().body(cursoAtualizado);
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }      
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso (@PathVariable long id){
        return cursoService.getCursoId(id).
                map(deletar -> {
                    cursoService.deletar(id);
                    return ResponseEntity.noContent().<Void>build();
                }).
                orElse(ResponseEntity.notFound().build());

    }




    
}
