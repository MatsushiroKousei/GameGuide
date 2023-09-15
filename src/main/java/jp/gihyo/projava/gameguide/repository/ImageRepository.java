package jp.gihyo.projava.gameguide.repository;

import jp.gihyo.projava.gameguide.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> { //entityを元にDBを操作
}
