package cn.andybao.dao;

public interface UserMapper {
    /**
     * 根据用户id查询用户信息
     */
    UserDO findUserById(Integer id);

}