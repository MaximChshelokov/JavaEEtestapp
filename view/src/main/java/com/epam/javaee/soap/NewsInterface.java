package com.epam.javaee.soap;

import com.epam.javaee.entity.News;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface NewsInterface {

    @WebMethod
    boolean add(News news);

    @WebMethod
    boolean delete(Long id);

    @WebMethod
    boolean update(News news);

    @WebMethod
    News get(Long id);

    @WebMethod
    List<News> getAll();
}
