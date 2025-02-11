class Receipt:
    def __init__(self, receiptDate, receiptAmount):
        self.receiptDate = receiptDate
        self.receiptAmount = receiptAmount

    def getReceiptAmount(self):
        return self.receiptAmount
    
r1 = Receipt("7/24/24", 101)
r2 = Receipt("6/1/24", 45)

receiptList = [r1, r2]

