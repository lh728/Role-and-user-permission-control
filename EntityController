package com.example.controller;


import com.example.dao.AuthorityDao;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.Authority;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller


public class EntityController {
    @Autowired
    private UserDao userRepository;
    @Autowired
    private RoleDao roleRepository;
    @Autowired
    private AuthorityDao authorityRepository;
    @Autowired
    private EntityService entityService;

    /*
        用户部分的增删查改
     */
    @RequestMapping("/finduser")
    @ResponseBody
    public List<User> findByName    (@RequestParam(value = "userName") String userName) {
        return userRepository.findAllByUserName(userName);
    }

    @PostMapping("findalluser/{userId}")
    @ResponseBody
    public List<User> findById(@PathVariable("userId") Integer userId) {
        return userRepository.findAllByUserId(userId);
    }
    @RequestMapping("/adduser")

    public String addUser(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "roleName") String roleName
                              ) {
        User user = new User();
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        user.setUserName(userName);
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);//给用户设置权限
        userRepository.save(user);
        return "redirect:/quanxian";
    }

    @GetMapping("addrole/{userName}")
    public ModelAndView findByName(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("quanxian",userRepository.findAllByUserName(userName));
        return new ModelAndView("/addrole","model",model);
    }

    @RequestMapping("/adduserrole")
    @ResponseBody
    public List<User> addUserRole(@RequestParam(value = "userName") String userName,
                                  @RequestParam(value = "roleName") String roleName) {
        User user = userRepository.findAllByUserName(userName).get(0);
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        }
        user.getRoles().add(role);//给用户设置权限
        userRepository.save(user);
        return userRepository.findAll();
    }

    @GetMapping("/deleteByUserName/{userName}")
    public ModelAndView deleteByUserName(@PathVariable("userName") String userName){
        System.out.println("username="+userName);
        entityService.deleteByUserName(userName);
        return  new ModelAndView("redirect:/quanxian");
    }

    /*
        查询用户权限
     */
    @RequestMapping("/getauth")
    @ResponseBody
    public Set<Authority> getAuthority(
            @RequestParam(value = "userName") String userName) {
        Set<Authority> authoritieSet = new HashSet<>();
        User user = userRepository.findAllByUserName(userName).get(0);
        for(Role role : user.getRoles()){
            for(Authority authority : role.getAuthorities()) {
                authoritieSet.add(authority);
            }
        }
        return authoritieSet;
    }

    /*
        角色部分的增删查改
     */
    @RequestMapping("/findallrole")
    @ResponseBody
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @RequestMapping("/addrole")

    public String addRole(
            @RequestParam(value = "roleName") String roleName,
            @RequestParam(value = "authName") String authName) {
        Role role = new Role();
        Authority authority = authorityRepository.findAllByAuthorityName(authName).get(0);
        role.setRoleName(roleName);
        role.setAuthorities(new ArrayList<>());
        role.getAuthorities().add(authority);
        roleRepository.save(role);
        return "redirect:/quanxian";
    }

    /*
        给角色添加权限
     */
    @RequestMapping("/addroleauth")
    @ResponseBody
    public List<Role> addRoleAuth(
            @RequestParam(value = "roleName") String roleName,
            @RequestParam(value = "authName") String authName) {
        Role role = roleRepository.findAllByRoleName(roleName).get(0);
        Authority authority = authorityRepository.findAllByAuthorityName(authName).get(0);
        if (role.getAuthorities() == null) {
            role.setAuthorities(new ArrayList<>());
        }
        role.getAuthorities().add(authority);
        roleRepository.save(role);
        return roleRepository.findAll();
    }

    @GetMapping("/deleteByRoleName/{roleName}")
    public ModelAndView deleteByRoleName(@PathVariable("roleName") String roleName){
        entityService.deleteByRoleName(roleName);
        return  new ModelAndView("redirect:/quanxian");
    }


    /*
        权限部分的增删查改
     */
    @RequestMapping("/findallauth")
    @ResponseBody
    public List<Authority> findAllAuthority() {
        return authorityRepository.findAll();
    }

    @GetMapping("/deleteByAuthorityName/{authorityName}")
    public ModelAndView deleteByAuhorityName(@PathVariable("authorityName") String authorityName){
        entityService.deleteByAuthorityName(authorityName);
        return new ModelAndView("redirect:/quanxian");
    }

    /*
    在前端显示用户，角色和权限
*/
    @RequestMapping("/quanxian")
    public String Ask(Model model) {
        List<User> findUser = userRepository.findAll();
        List<Role> findRole = roleRepository.findAll();
        List<Authority> findAuth = authorityRepository.findAll();
        model.addAttribute("findUser", findUser);
        model.addAttribute("findRole", findRole);
        model.addAttribute("findAuth", findAuth);
        return "/quanxian";
    }

}
