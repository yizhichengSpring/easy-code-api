package ${controller.packageName};
<#if controller.openSwagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
/**
* @author ${controller.auther}
* @Description ${controller.description}
* @Date ${controller.createDate}
**/
@RestController
@RequestMapping("/user")
@Api(value = "登录模块",tags = "登录模块")
@Slf4j
public class ${controller.className} {

}