package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import watch.store.smart_web.entity.ColorEntity;
import watch.store.smart_web.repo.ColorRepo;
import watch.store.smart_web.service.ColorService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepo repo;

    @Override
    public String findById(Long id) {
        ColorEntity entity = repo.findByDeleteFlagIsFalseAndId(id);
        if(entity != null){
            return entity.getValueColor();
        }
        return null;
    }
}
