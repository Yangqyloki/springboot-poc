package cx.sap.panda.unitservice.utils;

import cx.sap.panda.unitservice.dto.CustomerDTO;
import cx.sap.panda.unitservice.dto.OccCustomerDTO;
import cx.sap.panda.unitservice.aop.dto.KymaValidatorRequestDTO;

import static cx.sap.panda.unitservice.constants.UnitServiceConstants.DEFAULT_PWD;

public class DTOConverter {

    public static OccCustomerDTO convertCustomer(final CustomerDTO customer) {
        final OccCustomerDTO occCustomerDTO = new OccCustomerDTO();
        occCustomerDTO.setFirstName(customer.getFirstName());
        occCustomerDTO.setLastName(customer.getLastName());
        occCustomerDTO.setPassword(DEFAULT_PWD);
        occCustomerDTO.setTitleCode(customer.getTitle());
        occCustomerDTO.setUid(customer.getEmail());
        return occCustomerDTO;
    }

//    public static MemberListDTO convertMemberList(final CustomerDTO customer) {
//        final MemberDTO member = new MemberDTO();
//        member.setName(customer.getLastName() + " " + customer.getFirstName());
//        member.setUid(customer.getEmail());
//        return new MemberListDTO(List.of(member));
//    }

    public static KymaValidatorRequestDTO convertValidateRequestBody(final CustomerDTO customer) {
        final KymaValidatorRequestDTO validateUserRequest = new KymaValidatorRequestDTO();
        validateUserRequest.setEmail(customer.getEmail());
        validateUserRequest.setMobile(customer.getMobile());
        return validateUserRequest;
    }
}
