package watch.store.smart_web.service;


import watch.store.smart_web.dto.respone.product.ProductDetailStatusRespone;

import java.util.List;

public interface ProductDetailStatusService {

    List<ProductDetailStatusRespone> findByRomId(Long romId);
}
