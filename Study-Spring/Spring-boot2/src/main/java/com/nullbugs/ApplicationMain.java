package com.nullbugs;


import com.nullbugs.pojo.Student;
import com.nullbugs.pojo.StudentVali;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackageClasses = ApplicationMain.class)
@RestController
public class ApplicationMain {

    @Autowired
    private StudentVali validate ;

    @InitBinder
    public void initBinder(DataBinder dataBinder){
        dataBinder.setValidator(validate);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class,args );
    }

    @RequestMapping(value = "/bt")
    public void getS(@Validated Student stu, BindResult result){

        System.out.println(stu);
    }
}
