package htec.airlines.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.CommentDto;
import htec.airlines.service.CommentService;
import htec.airlines.utility.JWTTokenUtil;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private JWTTokenUtil tokenUtil;
	
	@PostMapping("/add")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
		String username = tokenUtil.getUsernameFromToken(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", ""));
		return ResponseEntity.ok(commentService.createComment(commentDto, username));
	}
	
	@PutMapping("/update")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
		String username = tokenUtil.getUsernameFromToken(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", ""));
		return ResponseEntity.ok(commentService.updateComment(commentDto, username));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteComment(@PathVariable("id") Long id, HttpServletRequest request) {
		String username = tokenUtil.getUsernameFromToken(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", ""));
		return ResponseEntity.ok(commentService.deleteComment(id, username));
	}
}
