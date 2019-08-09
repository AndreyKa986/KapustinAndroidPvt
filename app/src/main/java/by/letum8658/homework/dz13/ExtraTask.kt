package by.letum8658.homework.dz13

class ExtraTask {

    fun checkWin(codeList: List<List<String>>, shoppingCart: List<String>): Int {

        var numberOfGroup = 0
        var number = 0

        while (number < shoppingCart.size) {
            if (shoppingCart[number] == codeList[numberOfGroup][0]) {
                val size = codeList[numberOfGroup].size
                if ((number + size) <= shoppingCart.size) {
                    if (equalsLists(
                            codeList[numberOfGroup],
                            shoppingCart.subList(number, number + size)
                        )
                    ) {
                        numberOfGroup++
                        if (numberOfGroup == codeList.size) break
                        number += size
                    } else number++
                } else break
            } else number++
        }

        return if (numberOfGroup == codeList.size) 1 else 0
    }

    private fun equalsLists(codeList: List<String>, shoppingCart: List<String>): Boolean {
        var answer = true

        for (i in 0 until (codeList.size)) {
            if (codeList[i] != shoppingCart[i] && codeList[i] != "anything") {
                answer = false
                break
            }
        }

        return answer
    }
}