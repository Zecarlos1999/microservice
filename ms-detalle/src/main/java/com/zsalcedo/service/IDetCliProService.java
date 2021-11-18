package com.zsalcedo.service;

import java.util.List;

import com.zsalcedo.model.DetCliPro;

public interface IDetCliProService {

	public List<DetCliPro> findAll();

	public DetCliPro findById(String id);

	public DetCliPro save(DetCliPro detCliPro);

	public DetCliPro update(DetCliPro clidetCliProent);

	public void deleteById(String id);
}
