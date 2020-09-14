/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import exception.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Pessoa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepo;
    
    @GetMapping("/pessoa")
    public List<Pessoa> gteAllPessoa(){
        return pessoaRepo.findAll();
    }
    
    @GetMapping("/pessoa/{codigo}")
    public ResponseEntity<Pessoa> getEmployeeById(@PathVariable(value = "codigo") Long pessoaCod)
        throws ResourceNotFoundException {
        Pessoa pessoa = pessoaRepo.findById(pessoaCod)
          .orElseThrow(() -> new ResourceNotFoundException("Pessoa not found for this id :: " + pessoaCod));
        return ResponseEntity.ok().body(pessoa);
    }
    
    @PostMapping("/pessoa")
    public Pessoa createPessoa(@Valid @RequestBody Pessoa pessoa) {
        return pessoaRepo.save(pessoa);
    }
    
    @PutMapping("/pessoa/{codigo}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable(value = "codigo") Long pessoaCod,
         @Valid @RequestBody Pessoa pessoaDetails) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaRepo.findById(pessoaCod)
        .orElseThrow(() -> new ResourceNotFoundException("Pessoa not found for this id :: " + pessoaCod));

        pessoa.setEmail(pessoaDetails.getEmail());
        pessoa.setEndereco(pessoaDetails.getEndereco());
        pessoa.setNome(pessoaDetails.getNome());
        final Pessoa updatedPessoa = pessoaRepo.save(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }
    
    @DeleteMapping("/pessoa/{codigo}")
    public Map<String, Boolean> deletePessoa(@PathVariable(value = "codigo") Long pessoaCod)
         throws ResourceNotFoundException {
        Pessoa pessoa = pessoaRepo.findById(pessoaCod)
       .orElseThrow(() -> new ResourceNotFoundException("Pessoa not found for this id :: " + pessoaCod));

        pessoaRepo.delete(pessoa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
