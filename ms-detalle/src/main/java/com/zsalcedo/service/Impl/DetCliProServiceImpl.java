package com.zsalcedo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsalcedo.model.DetCliPro;
import com.zsalcedo.repository.IDetCliProRepository;
import com.zsalcedo.service.IDetCliProService;


//@Slf4j
@Service
public class DetCliProServiceImpl implements IDetCliProService {

	@Autowired
	private IDetCliProRepository detCliProRepository;

	@Override
	public List<DetCliPro> findAll() {
		return detCliProRepository.findAll();
		 
	}

	@Override
	public DetCliPro findById(String id) {
		return detCliProRepository.findById(id).orElse(null);
	}

	@Override
	public DetCliPro save(DetCliPro detCliPro) {
		return detCliProRepository.save(detCliPro);
	}

	@Override
	public DetCliPro update(DetCliPro detCliPro) {
		return detCliProRepository.save(detCliPro);
	}

	@Override
	public void deleteById(String id) {
		detCliProRepository.deleteById(id);
	}

}
