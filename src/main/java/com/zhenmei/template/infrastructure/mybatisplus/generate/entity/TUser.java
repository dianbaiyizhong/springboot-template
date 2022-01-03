package com.zhenmei.template.infrastructure.mybatisplus.generate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nntk
 * @since 2022-01-03
 */
@TableName("T_USER")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键ID
     */
        private Long id;

      /**
     * 姓名
     */
      private String name;

      /**
     * 年龄
     */
      private Integer age;

      /**
     * 邮箱
     */
      private String email;

    
    public Long getId() {
        return id;
    }

      public void setId(Long id) {
          this.id = id;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public Integer getAge() {
        return age;
    }

      public void setAge(Integer age) {
          this.age = age;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }

    @Override
    public String toString() {
        return "TUser{" +
              ", id=" + id +
                  ", name=" + name +
                  ", age=" + age +
                  ", email=" + email +
              "}";
    }
}
