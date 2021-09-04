package com.rental_list.model;

import java.util.*;

public interface RentalListDAO_interface {
	public void insert(RentalListVO rentalListVO);
    public void update(RentalListVO rentalListVO);
    public void delete(Integer rentalListSN);
    public RentalListVO findByPrimaryKey(Integer rentalListSN);
    public List<RentalListVO> getAll();
}
