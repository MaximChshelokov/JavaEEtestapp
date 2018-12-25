package com.epam.javaee.rest;

import com.epam.javaee.entity.News;
import com.epam.javaee.service.NewsServiceLocal;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/news")
public class NewsController {

    private static final String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON + ";charset=utf-8";

    @Inject
    private NewsServiceLocal newsService;

    @POST
    @Consumes(APPLICATION_JSON_UTF8)
    public Response create(News news) {
        newsService.addNews(news);
        return Response.accepted(news).build();
    }

    @DELETE
    @Path("/{id:[0-9]*}")
    public Response delete(
        @PathParam("id")
            Long id) {
        newsService.deleteNews(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON_UTF8)
    @Path("/{id:[0-9]*}")
    public Response getById(
        @PathParam("id")
            Long id) {
        News news = newsService.getNews(id);
        if (news == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(news).build();
    }

    @GET
    @Produces(APPLICATION_JSON_UTF8)
    public Response getAll() {
        List newsList = newsService.getAllNews();
        if (newsList.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(newsList).build();
    }

    @PUT
    @Consumes(APPLICATION_JSON_UTF8)
    public Response update(News news) {
        newsService.updateNews(news);
        return Response.noContent().build();
    }
}
