package com.web.core.dao.base;

import com.web.core.utility.ResolverUtil;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by Edmund on 2015/7/12.
 */
public class PackageAnnotationSessionFactoryBean extends LocalSessionFactoryBean
{
    private String[] annotatedPackage;


    public void setAnnotatedPackage(String[] annotatedPackage)
    {
        this.annotatedPackage = annotatedPackage;

        ResolverUtil<Entity> resolver = new ResolverUtil<Entity>();

        // ��������������@Entity��Class
        resolver.findAnnotated(Entity.class,annotatedPackage);
        Set<Class<? extends Entity>> beans = resolver.getClasses();

        Class[] classA = new Class[beans.size()];
        beans.toArray(classA);
        super.setAnnotatedClasses(classA);//����Spring��AnnotationSessionFactoryBean��setAnnotatedClasses������������Entityע��
    }


    public String[] getAnnotatedPackage()
    {
        return annotatedPackage;
    }
}
