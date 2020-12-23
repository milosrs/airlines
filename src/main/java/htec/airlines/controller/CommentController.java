package htec.airlines.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.CommentDto;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addComment(CommentDto commentDto) {
		return true;
	}
	
	@PutMapping("/update")
	public boolean updateComment(CommentDto commentDto) {
		return true;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteComment(@RequestParam("id") Long id) {
		return true;
	}
}
