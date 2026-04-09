package com.kiran.hospital.security;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.kiran.hospital.entity.type.PermissionType;
import com.kiran.hospital.entity.type.RoleType;
import static com.kiran.hospital.entity.type.RoleType.*;
import static com.kiran.hospital.entity.type.PermissionType.*;

public class RolePermissionMapping {
	
	private static final Map<RoleType, Set<PermissionType>> map =  Map.of(
				PATIENT, Set.of( PATIENT_READ, APPOINTMENT_READ, APPOINTMENT_WRITE),
				DOCTOR, Set.of( PATIENT_READ, APPOINTMENT_READ, APPOINTMENT_WRITE, APPOINTMENT_DELETE),
				ADMIN, Set.of(PATIENT_WRITE, PATIENT_READ, APPOINTMENT_READ, APPOINTMENT_WRITE, APPOINTMENT_DELETE, USER_MANAGE, REPORT_VIEW )
			);
	
	public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType role){
		return map.get(role).stream()
				.map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
	}

}
