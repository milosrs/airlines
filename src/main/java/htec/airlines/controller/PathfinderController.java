package htec.airlines.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.FindPathDto;
import htec.airlines.dto.PathDto;
import htec.airlines.service.PathFindService;

@RestController
@RequestMapping("/api/pathfind")
public class PathfinderController {
	@Autowired
	private PathFindService pathFindService;
	
	@PostMapping("/find")
	public ResponseEntity<Collection<PathDto>> getPath(@RequestBody FindPathDto dto) throws Exception {
		return ResponseEntity.ok(pathFindService.findPath(dto.getSrcCity(), dto.getDstCity()));
	}
}
