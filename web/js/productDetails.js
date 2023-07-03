// For fun since I don't know much about JS

let quantity = document.querySelector('#quantity');
let minusButton = document.querySelector('.quantity-left-minus');
let plusButton = document.querySelector('.quantity-right-plus');

minusButton.addEventListener('click', function() {
    let currentQuantity = parseInt(quantity.value);
    if (currentQuantity > 0) {
        quantity.value = currentQuantity - 1;
    }
});

plusButton.addEventListener('click', function() {
    let currentQuantity = parseInt(quantity.value);
    quantity.value = currentQuantity + 1;
});
