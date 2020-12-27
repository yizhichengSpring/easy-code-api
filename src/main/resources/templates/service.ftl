package ${service.packageName};
import org.springframework.web.bind.annotation.*;
/**
* @author ${service.auther}
* @Description ${service.description}
* @Date ${service.createDate}
**/
public interface ${service.className} {
    /**
    * 新增
    * @param
    * @return
    */
    Result add();
    /**
    * 删除
    * @param
    * @return
    */
    Result del();
    /**
    * 修改
    * @param
    * @return
    */
    Result update();
    /**
    * 查询
    * @param
    * @return
    */
    Result select();


}