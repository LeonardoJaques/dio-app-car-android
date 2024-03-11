package br.com.jaquesprojetos.eletriccarapp.data

import br.com.jaquesprojetos.eletriccarapp.domain.Car

object CarFactory {
    val list = listOf(
        Car(
            1,
            "R$ 200.000,00",
            "200cv",
            "300kWh",
            "1000km",
            "https://www.tesla.com/sites/default/files/images/model-s/model-y-design-desktop.jpg"
        ),
        Car(
            2,
            "R$ 300.000,00",
            "300cv",
            "400kWh",
            "1500km",
            "https://www.tesla.com/sites/default/files/images/model-s/model-y-design-desktop.jpg"
        ),
        Car(
            3,
            "R$ 400.000,00",
            "400cv",
            "500kWh",
            "2000km",
            "https://www.tesla.com/sites/default/files/images/model-s/model-y-design-desktop.jpg"
        ),
        Car(
            4,
            "R$ 500.000,00",
            "500cv",
            "600kWh",
            "2500km",
            "https://www.tesla.com/sites/default/files/images/model-s/model-y-design-desktop.jpg"
        ),
    )
}