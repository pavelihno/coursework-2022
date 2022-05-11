const formCreateOrder = document.querySelector("#create-order-form");
const orderPrice = document.querySelector("#order-price");
const buttonsAddOrder = document.querySelectorAll(".add-order")
const addedProducts = document.querySelector("#added-products")
const withDelivery = document.querySelector("#withDelivery")
const deliveryPrice = document.querySelector("#delivery-price")

if(formCreateOrder) {
    const updatePrice = () => {
        const addedProductsToOrder = [...addedProducts.querySelectorAll(".collection-product")]
        let sumPrice = addedProductsToOrder.reduce((acc, item) => {
            return acc + Number(item.dataset.price);
        }, 0)

        if (withDelivery.checked) {
            sumPrice += parseInt(deliveryPrice.textContent);
        }

        orderPrice.textContent = `${sumPrice}`
    }
    updatePrice()
    buttonsAddOrder.forEach(btn => {
        btn.addEventListener("click", (event) => {
            const target = event.target
            const inputs = formCreateOrder.querySelectorAll(".product-item");
            const index = !inputs.length
                ? 0
                : Number(inputs[inputs.length - 1].dataset.index) + 1
            const addedInputHtml = `<input type="text" data-index="${index}" hidden class="product-item" name="productsIds[]" field="productsIds[]" value="${target.dataset.id}">`
            const addedListHtml = `
                <li class="collection-product list-group-item" data-index="${index}" data-price="${target.dataset.price}" data-id="${target.dataset.id}">
                    <span class="font-weight-bold">${target.dataset.name}</span>
                    <u>${target.dataset.price} рублей</u>
                    <button class="btn btn-danger btn-sm">Удалить</button>
                </li>
            `
            formCreateOrder.insertAdjacentHTML("beforeend", addedInputHtml)
            addedProducts.insertAdjacentHTML("beforeend", addedListHtml)
            updatePrice();
        })
    })
    addedProducts.addEventListener("click", (event) => {
        const colItem = event.target.closest(".collection-product")
        const removedIndex = colItem?.dataset.index
        const input = formCreateOrder.querySelector(`.product-item[data-index='${removedIndex}']`)
        input.remove();
        colItem.remove();
        updatePrice();
    })
    if(withDelivery) {
        withDelivery.addEventListener("change", (event) => {
            updatePrice();
        })
    }
}
