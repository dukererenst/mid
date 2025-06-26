/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.indexgenesys.mid.common;

import com.ctc.wstx.shaded.msv_core.reader.GrammarReader;
import com.indexgenesys.mid.entity.enums.BatchStatus;
import com.indexgenesys.mid.entity.enums.CompanyType;
import com.indexgenesys.mid.entity.enums.CustomerType;
import com.indexgenesys.mid.entity.enums.Gender;
import com.indexgenesys.mid.entity.enums.ModeOfPayment;
import com.indexgenesys.mid.entity.enums.PriceType;
import com.indexgenesys.mid.entity.enums.StickerRequestStatus;
import com.indexgenesys.mid.entity.enums.StickerStatus;
import com.indexgenesys.mid.entity.enums.VehicleUsageType;
import com.indexgenesys.mid.entity.mid.Items;
import com.indexgenesys.mid.entity.mid.ProductType;
import com.indexgenesys.mid.entity.mid.RiskCategory;
import com.indexgenesys.mid.entity.mid.VehicleBodyType;
import com.indexgenesys.mid.entity.mid.VehicleMake;
import com.indexgenesys.mid.entity.mid.VehicleModel;
import com.indexgenesys.mid.entity.mid.VehicleType;
import com.indexgenesys.mid.entity.setting.CompanyInformation;
import com.indexgenesys.mid.entity.setting.Region;
import com.indexgenesys.mid.entity.sticker.StickerBatch;
import com.indexgenesys.mid.service.MidService;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ernest
 */
@Named(value = "commonOption")
@SessionScoped
public class CommonOption implements Serializable {

    @Inject
    MidService midService;
    

    public CommonOption() {
    }
    
    public List<Gender> getGenders() {
    return Arrays.asList(Gender.values());
}

    public List<CompanyType> companyType() {
        return Arrays.asList(CompanyType.values());
    }
    public List<CustomerType> customerType() {
        return Arrays.asList(CustomerType.values());
    }

    public List<PriceType> priceType() {
        return Arrays.asList(PriceType.values());
    }
    
    public List<VehicleUsageType> vehicleUsageType() {
        return Arrays.asList(VehicleUsageType.values());
    }
    public List<ModeOfPayment> modeOfPayment() {
        return Arrays.asList(ModeOfPayment.values());
    }
    public List<StickerRequestStatus> stickerRequestStatus() {
        return Arrays.asList(StickerRequestStatus.values());
    }
    public List<StickerStatus> stickerStatus() {
        return Arrays.asList(StickerStatus.values());
    }
    
  
    public List<CompanyInformation> insuranceCompanysList() {
        return midService.findAll(CompanyInformation.class, "companyName");
    }
    
    public List<RiskCategory> riskCategoryList() {
        return midService.findAll(RiskCategory.class, "categoryName");
    }
    
    public List<VehicleType> vehicleTypeList() {
        return midService.findAll(VehicleType.class, "vehicleTypeName");
    }
    
    public List<ProductType> productTypeList() {
        return midService.findAll(ProductType.class, "productTypeName");
    }
    
    public List<StickerBatch> stickerBatchList() {
        return midService.findAll(StickerBatch.class, "batchName");
//        return midService.findAll(StickerBatch.class, "batchName", "batchStatus", BatchStatus.ACTIVE);
        
    }


    public List<Region> regionList() {
        return midService.findAll(Region.class, "regionName");
    }

    public List<VehicleMake> vehicleMakeList() {
        return midService.findAll(VehicleMake.class, "vehicleMakeName");
    }
    public List<VehicleModel> vehicleModelList() {
        return midService.findAll(VehicleModel.class, "vehicleModelName");
    }
    public List<VehicleBodyType> vehicleBodyTypeList() {
        return midService.findAll(VehicleBodyType.class, "vehicleBodyTypeName");
    }
    
    public List<Items> itemsList() {
        return midService.findAll(Items.class, "itemName");
    }

}
