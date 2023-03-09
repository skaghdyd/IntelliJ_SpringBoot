package com.study.springboot.controller;

import java.util.List;
import java.util.Optional;

import com.study.springboot.entity.Genre;
import com.study.springboot.entity.GenreDto;
import com.study.springboot.entity.Movie;
import com.study.springboot.entity.MovieDto;
import com.study.springboot.repository.GenreRepository;
import com.study.springboot.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import static java.util.Arrays.stream;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MyController {

	//private final Movie movie;
	//private final Genre genre;
	private final GenreRepository genreRepository;
	private final MovieRepository movieRepository;
	
	@GetMapping("/")
	public String root() {
		return "view/firstpage";
	}
	
	@GetMapping("/actionList")
	public String action() {
		return "view/action";
	}
	
	@GetMapping("/movie/{genre}")
	public String movie(@PathVariable("genre") String genre, Model model) {
		if(genre.equals("action")) {
		
			return "view/action";
		}else if(genre.equals("family")) {
			return "view/family";
		}else if(genre.equals("comic")) {
			return "view/comic";
		}else if(genre.equals("horror")) {
			return "view/horror";
		}else if(genre.equals("scienceFiction")) {
			return "view/scienceFiction";
		}else {
			return "/";
		}
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		List<Genre> genreList = genreRepository.findAll();
		//System.out.println("genreList--------"+genreList);
		//log.info("genreList--------"+genreList);
		model.addAttribute("genreList", genreList);

		List<Movie> movieAllList = movieRepository.findAll();
		model.addAttribute("movieAllList", movieAllList);
		//movieAllList.stream().forEach((i)-> System.out.println(i));

		return "view/admin";
	}

	@PostMapping("/insert")
	public String insertMovie(Movie movie){
		System.out.println("----form으로 받아온 정보-------");
		System.out.println(movie);
		Genre movieGenre = movie.getGenre();
		int size = movieGenre.getMovieList().size()+1;
		movie.setIdx(size);
		System.out.println("setidx이후---------:");
		System.out.println(movie);
		movieRepository.save(movie);
		return "redirect:/admin";
	}
	@ResponseBody
	@PostMapping ("/deleteMovie")
	public void deleteMovie(@RequestBody Movie movie){
		Long did = movie.getDid();
		log.info("넘어온 did------:"+did);
		movieRepository.deleteById(did);

	}

	@PostMapping("/editModal")
	@ResponseBody
	public MovieDto editModal(@RequestBody Movie movie, Model model){
		log.info("수정클릭시 넘어온 did-------:"+movie);
		Long did = movie.getDid();

		Movie smovie = movieRepository.findById(did).orElseThrow();
		log.info("smovie-----------:"+smovie);

		GenreDto genreDto = GenreDto.builder()
				.gid(smovie.getGenre().getGid())
				.genreName(smovie.getGenre().getGenreName())
				.build();

		MovieDto movieDto = MovieDto.builder()
				.did(smovie.getDid())
				.title(smovie.getTitle())
				.content(smovie.getContent())
				.idx(smovie.getIdx())
				.genreDto(genreDto)
				.build();

		log.info("movieDto >>> " + movieDto);

		return movieDto;
	}
}
