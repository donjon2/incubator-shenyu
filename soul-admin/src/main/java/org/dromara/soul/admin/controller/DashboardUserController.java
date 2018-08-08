package org.dromara.soul.admin.controller;

import org.dromara.soul.admin.dto.DashboardUserDTO;
import org.dromara.soul.admin.page.PageParameter;
import org.dromara.soul.admin.query.DashboardUserQuery;
import org.dromara.soul.admin.service.DashboardUserService;
import org.dromara.soul.common.result.SoulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * this is dashboard user controller.
 *
 * @author jiangxiaofeng(Nicholas)
 */
@RestController
@RequestMapping("/dashboardUser")
public class DashboardUserController {

    private final DashboardUserService dashboardUserService;

    @Autowired(required = false)
    public DashboardUserController(final DashboardUserService dashboardUserService) {
        this.dashboardUserService = dashboardUserService;
    }

    /**
     * query dashboard users.
     *
     * @param userName    user name
     * @param currentPage current page
     * @param pageSize    page size
     * @return {@linkplain Mono}
     */
    @GetMapping("")
    public Mono<SoulResult> queryDashboardUsers(final String userName, final Integer currentPage, final Integer pageSize) {
        return Mono.create(soulResult -> soulResult.success(SoulResult.success(dashboardUserService.listByPage(
                new DashboardUserQuery(userName, new PageParameter(currentPage, pageSize))))));
    }

    /**
     * detail dashboard user.
     *
     * @param id dashboard user id.
     * @return {@linkplain Mono}
     */
    @GetMapping("/{id}")
    public Mono<SoulResult> detailDashboardUser(@PathVariable("id") final String id) {
        return Mono.create(soulResult -> soulResult.success(SoulResult.success(dashboardUserService.findById(id))));
    }

    /**
     * create dashboard user.
     *
     * @param dashboardUserDTO dashboard user.
     * @return {@linkplain Mono}
     */
    @PostMapping("")
    public Mono<SoulResult> createDashboardUser(@RequestBody final DashboardUserDTO dashboardUserDTO) {
        return Mono.create(soulResult -> soulResult.success(SoulResult.success(dashboardUserService.createOrUpdate(dashboardUserDTO))));
    }

    /**
     * update dashboard user.
     *
     * @param id               primary key.
     * @param dashboardUserDTO dashboard user.
     * @return {@linkplain Mono}
     */
    @PutMapping("/{id}")
    public Mono<SoulResult> updateDashboardUser(@PathVariable("id") final String id, @RequestBody final DashboardUserDTO dashboardUserDTO) {
        Objects.requireNonNull(dashboardUserDTO);
        dashboardUserDTO.setId(id);
        return Mono.create(soulResult -> soulResult.success(SoulResult.success(dashboardUserService.createOrUpdate(dashboardUserDTO))));
    }

    /**
     * delete dashboard user.
     *
     * @param id primary key.
     * @return {@linkplain Mono}
     */
    @DeleteMapping("/{id}")
    public Mono<SoulResult> deleteDashboardUser(@PathVariable("id") final String id) {
        return Mono.create(soulResult -> soulResult.success(SoulResult.success(dashboardUserService.delete(id))));
    }
}
