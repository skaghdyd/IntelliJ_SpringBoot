package com.study.springboot.controller;

import com.study.springboot.entity.Movie;
import com.study.springboot.entity.MovieCategory;
import com.study.springboot.entity.MoviePK;
import com.study.springboot.repository.MovieCategoryRepository;
import com.study.springboot.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
public class MyController {
	
	@Autowired
	MovieCategoryRepository movieCategoryRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);
		return "view/index";
	}

	@GetMapping("/insertMovieCategory")
	public String insertMovieCategory(Model model, @PageableDefault(page = 0, size = 3) Pageable pageable) {
		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);

		//페이징
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), list.size());
		final Page<MovieCategory> page = new PageImpl<>(list.subList(start, end), pageable, list.size());

		model.addAttribute("movieCategoryList", page);
		return "view/insertMovieCategory";
	}

	@PostMapping("/insertMovieCategory")
	public String insertMovieCategory(String name) {
//		MovieCategory movieCategory = new MovieCategory();
//		movieCategory.setName(name);
//		movieCategoryRepository.save(movieCategory);
		movieCategoryRepository.insertMovieCategory(name);
		return "redirect:/insertMovieCategory";
	}

	@GetMapping("/insertMovie")
	public String insertMovie(
			Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@PageableDefault(page = 0, size = 10) Pageable pageable
	) {
		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);

		List<Movie> movieList;
		if(type != null){
			model.addAttribute("type", type);
			movieList = movieRepository.selectByType(type);
		} else {
			movieList = movieRepository.findAll(
					Sort.by(
							Sort.Order.asc("moviePK.type"),
							Sort.Order.asc("groupIdx"),
							Sort.Order.asc("level"),
							Sort.Order.asc("levelIdx")
					)
			);
		}

		//페이징
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), movieList.size());
		final Page<Movie> page = new PageImpl<>(movieList.subList(start, end), pageable, movieList.size());

		model.addAttribute("movieList", page);
		return "view/insertMovie";
	}

	@PostMapping("/insertMovie")
	public String insertMovie(Movie movie, int type) {
		int idx_max = movieRepository.findMaxIdxByType(type);
		MoviePK moviePK = new MoviePK();
		moviePK.setType(type);
		moviePK.setIdx(idx_max);
//		movie.setGroupIdx(idx_max);
//		movie.setLevel(1);
//		movie.setLevelIdx(1);
//		movie.setMoviePK(moviePK);
		MovieCategory movieCategory = movieCategoryRepository.findById(type).orElseThrow();
//		movie.setMovieCategory(movieCategory);
		System.out.println(movie);

		Movie movie2 = Movie.builder()
				.moviePK(moviePK)
				.groupIdx(idx_max)
				.movieCategory(movieCategory)
				.title(movie.getTitle())
				.content(movie.getContent())
				.build();
		System.out.println(movie2);
		movieRepository.save(movie2);
		return "redirect:/insertMovie";
	}

	@GetMapping("/movie/{type}")
	public String movie(@PathVariable int type, Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);
		List<Movie> movieList = movieRepository.selectByType(type);

		//페이징
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), movieList.size());
		final Page<Movie> page = new PageImpl<>(movieList.subList(start, end), pageable, movieList.size());

		model.addAttribute("movieList", page);
		model.addAttribute("type", type);
		return "view/movie";
	}

	@PostMapping("/movieDelete")
	public String movieDelete(@RequestParam("type") int type,
							  @RequestParam("idx") int idx,
							  Model model) {
		MoviePK moviePK = new MoviePK();
		moviePK.setType(type);
		moviePK.setIdx(idx);
		movieRepository.deleteById(moviePK);
		return "redirect:/insertMovie";
	}

	@PostMapping("/movieModify")
	public String movieModify(@RequestParam("type") int type,
							  @RequestParam("idx") int idx,
							  Model model) {
		MoviePK moviePK = new MoviePK();
		moviePK.setType(type);
		moviePK.setIdx(idx);

		Movie movie = movieRepository.findById(moviePK).orElseThrow();
		model.addAttribute("movie", movie);

		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);
		return "view/movieModify";
	}

	@PostMapping("/movieModifyAction")
	public String movieModifyAction(@RequestParam("type") int type,
									@RequestParam("idx") int idx,
									@RequestParam("title") String title,
									@RequestParam("content") String content,
									Model model) {
		movieRepository.updateByTypeAndIdx(type, idx, title, content);
		return "redirect:/insertMovie";
	}

	@PostMapping("/movieCategoryDelete")
	public String movieCategoryDelete(@RequestParam("type") int type, Model model) {
		movieCategoryRepository.deleteById(type);
		return "redirect:/insertMovieCategory";
	}

	@PostMapping("/movieCategoryModify")
	public String movieCategoryModify(@RequestParam("type") int type, Model model) {
		MovieCategory movieCategory = movieCategoryRepository.findById(type).orElseThrow();
		model.addAttribute("movieCategory", movieCategory);

		List<MovieCategory> list = movieCategoryRepository.findAll();
		model.addAttribute("list",list);
		return "view/movieCategoryModify";
	}

	@PostMapping("/movieCategoryModifyAction")
	public String movieModifyAction(@RequestParam("type") int type,
									@RequestParam("name") int name,
									Model model) {
		movieRepository.updateByTypeAndName(type, name);
		return "redirect:/insertMovieCategory";
	}

	@PostMapping("/writeChild")
	public String writeChild(@RequestParam("type") int type,
							 @RequestParam("idx") int idx,
							 Model model){

		MoviePK moviePK = new MoviePK();
		moviePK.setType(type);
		moviePK.setIdx(idx);
		Movie movie = movieRepository.findById(moviePK).orElseThrow(); //현재 선택한 글

		int idx_max = movieRepository.findMaxIdxByType(type); //idx max 값
		moviePK.setIdx(idx_max);

		int level_idx_max = movieRepository.findMaxLevelIdxByTypeAndIdxAndLevel(type, idx, movie.getLevel()+1);

		Movie movie2 = new Movie();
		movie2.setMoviePK(moviePK);
		movie2.setMovieCategory(movie.getMovieCategory());
		movie2.setGroupIdx(idx);
		movie2.setLevel(movie.getLevel()+1);
		movie2.setLevelIdx(level_idx_max);
		movie2.setTitle(movie.getTitle()+"의 댓글");
		movie2.setContent(movie.getContent()+"의 댓글");
		movieRepository.save(movie2);

		return "redirect:/movie/"+type;
	}
}
