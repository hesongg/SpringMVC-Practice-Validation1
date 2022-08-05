package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator  {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //isAssignableFrom 을 쓰면 자식 클래스까지 확인이 되므로 클래스 타입 비교 시 isAssignableFrom 을 쓰는게 좋음
        //item == clazz
        //item == subItem
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        //ValidationUtils를 이용하면 밑의 주석한 부분과 똑같은 기능을 구현 가능 -> 내부적으로 if문 사용해서 같다
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        /*
        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }
        */


        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
