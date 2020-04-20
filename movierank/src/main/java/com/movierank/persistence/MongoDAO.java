package com.movierank.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.movierank.domain.MovieDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MongoDAO {

	@Autowired
	private MongoOperations mongoOper;// sqlSession같은 존재
	
	//객체를 생성 이름을 자동으로 생성 첫글자를 소문자로 바꿔서 만들어준다. 
	
	
	public void save(MovieDTO mDto) {
		log.info(">>>>>>>>>>>영화 랭킹 정보 MongoDB에 저장");
		mongoOper.save(mDto);
		//인서트 id값이 중복되면 에러를 띄운다. 
		//save는 id 값이 중복되면  업그레이드를 해준다. 
	}
	
	public void dropCol() {
		log.info(">>>>>>>>>>>>>> Collection Drop");
		mongoOper.dropCollection("movie"); // 전부다 지워라 
	}
	//SELECT * FROM movie
	public List<MovieDTO> movieList(){
		log.info(">>>>>>>>>>> 영화 랭킹 정보 MongoDB에 저장");
		Criteria cri = new Criteria();// 키값
		//cri.is(value);
		Query query = new Query(cri);//밸류값
		List<MovieDTO> list = mongoOper.find(query, MovieDTO.class,"movie");// mongoOper는 쿼리를 날리는 것, 명령만하면 알아서 해준다, 어느정도 자동화가 된다.   
		for(MovieDTO one: list) {		//find의 결과를 list에 담아라						//디테일이 조금 떨어진다. 몽고템플릿은 직접 쿼리를 작성해서 날리는것, 
			log.info(one.toString());
		}
		//List<MovieDTO> list = mongoOper.find(query, entityClass);
		
		return list;
				
	}
}
