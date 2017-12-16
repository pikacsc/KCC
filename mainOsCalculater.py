#_*_ coding:utf8 _*_
# mainOsCalculater

import urllib.request
from bs4 import BeautifulSoup


fp = urllib.request.urlopen('http://info.finance.naver.com/marketindex/exchangeList.nhn')
source = fp.read()
fp.close()
class_list = ["tit","sale"]
soup = BeautifulSoup(source,'html.parser')
soup = soup.find_all("td", class_ = class_list)
money_data={}
for data in soup:
    if soup.index(data)%2==0:
        data=data.get_text().replace('\n','').replace('\t','')
        money_key=data
    elif soup.index(data)%2==1:
        money_value=data.get_text()
        money_data[money_key]=money_value
        money_key=None
        money_value=None
        


def menuOsCalculater ():
    print("")
    print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
    print("$                                                                 $")
    print("$     신용카드 해외 결제 최종 청구금액 계산기 ver.1.0             $")
    print("$                                                                 $")
    print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
    print("")
    print("")
    print("")
    print("")
    print("")
    print("")
    print("1.수동계산")
    print("")
    print("2.빠른계산(상품가격만 입력)")
    print("")
    print("3.빠른계산 설정")
    print("")
    print("4.도움말")
    print("")
    print("5.나가기")
    print("")
    print("")
    print("")
    print("")
    menuInput = input("원하는 항목의 번호를 입력하세요: ")
    if menuInput == "1":
        manuel()
    elif menuInput == "2":
        print("현재 개발중입니다")
        menuOsCalculater ()
    elif menuInput == "3":
        print("현재 개발중입니다")
        menuOsCalculater ()
    elif menuInput == "4":
        print("")
        print("")
        print("")
        print("도움말")
        print("1.수동계산:구매하고자 하는 해외상품 달러가격,신용카드의 브랜드,은행,달러환율 수동으로 직접 설정 하여 계산")
        print("")
        print("")
        print("2.빠른계산:구매하고자 하는 해외상품 달러가격만 입력하고 저장된 빠른계산설정에 맞춰서 바로 계산")
        print("")
        print("")
        print("3.빠른계산설정 변경, 신용카드 브랜드,은행,달러환율 등을 변경할 수 있음")
        for i in menuOsCalculater():
            menuOsCalculater()
    elif menuInput == "5":
        exit
    else:
        print("오류 잘못된 입력입니다")
            


    
def brandList(x):
    return {1: 0.011,
            2: 0.01,
            3: 0.014,
            4: 0,
            5: 0,
            6: 0,
            7: 0.008,
            8: 0.01,
            9: 0.015}.get(x, "No data")

def brandListPrint(x):
    return {1: "VISA",
            2: "Master",
            3: "Amex",
            4: "JCB",
            5: "Discover",
            6: "BC Global",
            7: "UnionPay",
            8: "URS",
            9: "ETC"}.get(x, "No data")

def cardList(x):
    return {1: 0.0018,
            2: 0.0018,
            3: 0.002,
            4: 0.002,
            5: 0.002,
            6: 0.0025,
            7: 0.0025,
            8: 0.003,
            9: 0.0035,
            10: 0.0035,
            11: 0.0035,
            12: 0.005}.get(x, "No data")

def cardListPrint(x):
    return {1: "신한카드",
            2: "현대카드",
            3: "외환카드",
            4: "삼성카드",
            5: "롯데카드",
            6: "국민카드",
            7: "씨티카드",
            8: "하나카드",
            9: "우리카드",
            10: "농협카드",
            11: "BC 글로벌카드",
            12: "ETC"}.get(x, "No data")

def manuel():
    try:
        goodsPriceInput = input("상품가격을 달러단위로 입력하세요 단 $달러기호는 생략: ")
        goodsPrice = float(goodsPriceInput)
        print("")
        print("")
        print("")
        print("1. VISA")
        print("2. Master")
        print("3. Amex")
        print("4. JCB")
        print("5. Discover")
        print("6. BC Global")
        print("7. UnionPay")
        print("8. URS")
        print("9. ETC")
        print("")
        print("")
        print("")
        print("입력한 상품가격",goodsPrice, "달러")
        print("")
        print("")
        brandSelect = float(input("브랜드를 선택하세요.(1-9): "))
        print("")
        print("")
        print("")
        selectedBrand = brandList(brandSelect)
        selectedBrandPrint = brandListPrint(brandSelect)
        brandCommission = round(float(goodsPrice*selectedBrand),2)
    #beforeWon:승인금액
        beforeWon = round(float(goodsPrice+brandCommission),2)
        wonSelect = input("실시간 환매도율을 적용하시겠습니까? y or n: ")
        if wonSelect == "y":
            won = round(wonExchange)
        if wonSelect == "n":
            wonInputOrNot = input("환율을 직접 원단위로 입력하세요 (원,\ 생략): ")
            won = float(wonInputOrNot)
        beforeCardCommission = beforeWon*won
        print("")
        print("")
        print("")
        print("1. 신한카드")
        print("2. 현대카드")
        print("3. 외환카드")
        print("4. 삼성카드")
        print("5. 롯데카드")
        print("6. 국민카드")
        print("7. 씨티카드")
        print("8. 하나카드")
        print("9. 우리카드")
        print("10. 농협카드")
        print("11. BC 글로벌카드")
        print("12. ETC")
        print("")
        print("")
        print("a.입력한 상품가격",goodsPrice, "달러")
        print("b.선택한 브랜드:",selectedBrandPrint)
        print("")
        print("")
        cardSelect = float(input("카드사를 선택하세요.(1-12): "))
        selectedCardPrint = cardListPrint(cardSelect)
        print("")
        print("")
        print("")
        print("")
        print("")
        print("")
        print("")
        print("")
        print("계산이 끝났습니다!")
        print("")
        print("")
        print("")
        print("a.입력한 상품가격",goodsPrice, "달러")
        print("")
        print("b.선택한 브랜드:",selectedBrandPrint)
        print("")
        print("c.선택한 카드사:",selectedCardPrint)
        print("")
        if wonSelect == "y":
            print("실시간 환매도율 적용")
        if wonSelect == "n":
            print("실시간 환매도율 적용안함, 설정된 환율",won,"원")
        print("")
        print("")
        selectedCard = cardList(cardSelect)  
        cardCommission = round(float(beforeCardCommission*selectedCard),0)
        afterCardCommission = round(float(beforeCardCommission+cardCommission))
        sumAllCommission = round(brandCommission*won+cardCommission)
        print("d.최종청구금액은",afterCardCommission,"원입니다.")
        print("")
        print("")
        print("")
        print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
        print("")
        print("a.상품가격:",goodsPrice,"달러")
        print("")
        print("b.선택한 브랜드:",selectedBrandPrint)
        print("")
        print("c.선택한 카드사:",selectedCardPrint)
        print("브랜드수수료율:",round(float(selectedBrand*100),2),"%")
        print("브랜드수수료:",brandCommission,"달러")
        print("")
        print("환매도율:"'"1달러당',won,'원"')
        print("")
        print("카드사수수료율:",round(float(selectedCard*100),2),"%")
        print("카드사수수료:",cardCommission,"원")
        print("카드사로 넘어오는 금액:",round(beforeCardCommission),"원")
        print("")
        print("d.최종청구금액:",afterCardCommission,"원")
        print("수수료합계:",sumAllCommission,"원")
        print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
        menuOsCalculater ()
    except ValueError:
        print ("오류: 숫자를 입력하지 않았습니다")
        menuOsCalculater ()
menuOsCalculater ()

    


