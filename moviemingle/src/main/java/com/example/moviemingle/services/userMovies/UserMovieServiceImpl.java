package com.example.moviemingle.services.userMovies;

import com.example.moviemingle.entities.Order;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.entities.UserMovie;
import com.example.moviemingle.repositories.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMovieServiceImpl implements UserMovieService{
    
    @Autowired
    private UserMovieRepository userMovieRepository;
    
    @Override
    public void createUserMovie(Order order) {
        List<UserMovie> userMovieList = new ArrayList<>();
        for(OrderDetail orderDetail : order.getOrderDetails()){
            UserMovie userMovie = new UserMovie();
            userMovie.setUser(order.getUser());
            userMovie.setMovie(orderDetail.getMovie());
            userMovie.setWatchStatus(false);
            userMovieList.add(userMovie);
        }
        userMovieRepository.saveAll(userMovieList);
    }
}
