package com.venthon.ecommerce.domain;

import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.event.OrderCancelledEvent;
import com.venthon.ecommerce.domain.event.OrderCreatedEvent;
import com.venthon.ecommerce.domain.event.OrderPaidEvent;

import java.util.List;
public interface OrderDomainService {

    /**
     * ផ្ទៀងផ្ទាត់ និងចាប់ផ្ដើមដំណើរការ order។
     * @param order ព័ត៌មានលម្អិតនៃការបញ្ជាទិញ ដែលត្រូវផ្ទៀងផ្ទាត់ និងដំណើរការ។
     * @param business អាជីវកម្មដែលត្រូវទទួលការកុម៉្មង់នេះ។
     * @return OrderCreatedEvent សម្រាប់បញ្ជាក់ថាការកុម្ម៉ងត្រូវបានបង្កើតឡើងដោយជោគជ័យ។
     */
    OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);


    /**
     * ទូទាត់តម្លៃនៃការបញ្ជាទិញ។
     * @param order ព័ត៌មានលម្អិតនៃការបញ្ជាទិញ ដែលត្រូវពិនិត្យ និងទូទាត់។
     * @return OrderPaidEvent សម្រាប់បញ្ជាក់ថាការបញ្ជាទិញត្រូវបានទូទាត់ដោយជោគជ័យ។
     */
    OrderPaidEvent payOrder(Order order);


    /**
     * យល់ព្រមលើការបញ្ជាទិញ
     * @param order ព័ត៌មានលម្អិតនៃការបញ្ជាទិញ ដែលត្រូវពិនិត្យ និងយល់ព្រម។
     */
    void approveOrder(Order order);


    /**
     * លុបចោលការបញ្ជាទិញក្រោយទូទាត់តម្លៃ
     * @param order ព័ត៌មានលម្អិតនៃការបញ្ជាទិញ ដែលត្រូវពិនិត្យ និងលុបចោល។
     * @param failureMessages ហេតុផលនៃការលុបចោលការបញ្ជាទិញ។
     * @return OrderCancelledEvent សម្រាប់បញ្ជាក់ថាការបញ្ជាទិញត្រូវបានលុបចោលជោគជ័យ។
     */
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);


    /**
     * លុបចោលការបញ្ជាទិញមុនទូទាត់តម្លៃ
     * @param order ព័ត៌មានលម្អិតនៃការបញ្ជាទិញ ដែលត្រូវពិនិត្យ និងលុបចោល។
     * @param failureMessages ហេតុផលនៃការលុបចោលការបញ្ជាទិញ។
     */
    void cancelOrder(Order order, List<String> failureMessages);


}
