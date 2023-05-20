package com.example.project3.service;

import com.example.project3.Model.Merchant;
import com.example.project3.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId() == id) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean addProuctToMerchant(int idMerchant, int productId, int stock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId() == idMerchant) {
                if (merchantStocks.get(i).getProductId() == productId) {
                    if (stock >= 0) {
                        merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasStock(int idMerchant, int productId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId() == idMerchant) {
                if (merchantStocks.get(i).getProductId() == productId) {
                    if (merchantStocks.get(i).getStock() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean reducStock(int idMerchant, int productId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantId() == idMerchant) {
                if (merchantStocks.get(i).getProductId() == productId) {
                    if (merchantStocks.get(i).getStock() > 0) {
                        merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - 1);
                        return true;
                    }
                }
            }
        }
        return false;

    }


}



