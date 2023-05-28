package com.qkd90.mybatisplus;

import cn.hutool.core.date.DateTime;
import com.github.javafaker.Faker;
import com.qkd90.mybatisplus.mapper.*;
import com.qkd90.mybatisplus.pojo.*;
import com.qkd90.mybatisplus.util.IdWorker;
import com.qkd90.mybatisplus.util.PasswordHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author renq@trasen.cn
 * @date 2023/4/11 16:25
 */
@RestController
public class fakeController {

    private static final Logger logger = LoggerFactory.getLogger(fakeController.class);
    @Resource
    private BsfUserMapper bsfUserMapper;
    @Resource
    private ThpsUserMapper thpsUserMapper;
    @Resource
    private LogUserMapper logUserMapper;
    @Resource
    private BsfEmployeeMapper bsfEmployeeMapper;
    @Resource
    private BsfEmpDeptMapper bsfEmpDeptMapper;
    @Resource
    private BsfOrganizationMapper bsfOrganizationMapper;
    @Resource
    private FakeOrgMapper fakeOrgMapper;

    @PostMapping("/test")
    public boolean test(@RequestParam Integer number) {
        return true;
    }


    @PostMapping("/addUser")
    public LogUser addUser(@RequestParam Integer number) {
        LogUser logUser = new LogUser();
        logUser.setPassword("trasen");
        logUser.setUsercode("admin");
        logUser.setId(123456789L);
        int result = logUserMapper.insert(logUser);
        // 受影响的行数
        logger.info("result = {}", result);
        return logUser;
    }

    @PostMapping("/addAllUser")
    public ThpsUser addThpsUser(@RequestParam Integer number) throws NoSuchAlgorithmException, InvalidKeySpecException {
        ThpsUser thpsUser = new ThpsUser();
        BsfUser bsfUser = new BsfUser();
        LogUser logUser = new LogUser();
        BsfEmployee bsfEmployee = new BsfEmployee();
        BsfEmpDept bsfEmpDept = new BsfEmpDept();

        Faker faker = new Faker(new Locale("zh-CN"));
        Faker enfaker = new Faker();
        TimeUnit minutes = TimeUnit.HOURS;
        int atmost = 365;

        for (int i = 0; i < number; i++) {
            String username = enfaker.name().lastName() + enfaker.name().firstName() + createCode();
            String password = enfaker.internet().password();
            long userId = IdWorker.getInstance().getId();
            Date date = faker.date().past(atmost, minutes);
            String chinesename = faker.name().name();
            String empcode = createCode();
            long empId = IdWorker.getInstance().getId();
            long empDeptId = IdWorker.getInstance().getId();

            bsfUser.setId(userId);
            bsfUser.setUsercode(username);
            bsfUser.setPassword(PasswordHash.createHash(password));
            bsfUser.setUsername(chinesename);
            bsfUser.setEmpId(empId);
            bsfUser.setUsertype("0");
            bsfUser.setCreateTime(DateTime.of(date));
            bsfUser.setUpdateTime(DateTime.of(date));

            thpsUser.setId(userId);
            thpsUser.setUsercode(username);
            thpsUser.setPassword(PasswordHash.createHash(password));
            thpsUser.setAutoExitTime(1440);

            logUser.setId(userId);
            logUser.setUsercode(username);
            logUser.setPassword(password);
            logUser.setEmpCode(empcode);

            bsfEmployee.setId(empId);
            bsfEmployee.setEmpName(chinesename);
            bsfEmployee.setEmpCode(empcode);

            bsfEmpDept.setId(empDeptId);
            bsfEmpDept.setEmpid(empId);
            bsfEmpDept.setDeptid(612281489899597824L);
            bsfEmpDept.setPrimaryflag("1");
            bsfEmpDept.setCreateTime(DateTime.of(date));
            bsfEmpDept.setUpdateTime(DateTime.of(date));


            int bsfResult = bsfUserMapper.insert(bsfUser);
            int thpsResult = thpsUserMapper.insert(thpsUser);
            int logResult = logUserMapper.insert(logUser);
            int employeeResult = bsfEmployeeMapper.insert(bsfEmployee);
            int empDeptResult = bsfEmpDeptMapper.insert(bsfEmpDept);

            // 受影响的行数
            logger.info("thpsResult = {}", thpsResult);
            logger.info("logResult = {}", logResult);
        }
        return thpsUser;
    }


    public static String createCode() {
        Random random = new Random();
        char c = (char) (random.nextInt(26) + 'A');
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            str.append(random.nextInt(10));
        }
        String code = c + str.toString();
        System.out.println(code);
        return code;
    }

    @PostMapping("/addOrg")
    public boolean addOrg() {
        Faker enfaker = new Faker();
        FakeOrg fakeOrg = new FakeOrg();
        BsfOrganization bsfOrganization = new BsfOrganization();

        for (int i = 0; i < 100; i++) {
            FakeOrg fakeOrgName = fakeOrgMapper.selectById(i);
            bsfOrganization.setId(IdWorker.getInstance().getId());
            bsfOrganization.setParentid(0L);
            bsfOrganization.setManagetype("2");
            bsfOrganization.setOrgcode("430" + RandomDigit());
            bsfOrganization.setOrgname(fakeOrgName.getOrgname());

            int bsfOrgResult = bsfOrganizationMapper.insert(bsfOrganization);

            logger.info("bsfOrgResult = {}", bsfOrgResult);
        }
        return true;
    }

    public static String RandomDigit() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}

